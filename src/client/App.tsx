import React, { useEffect, useRef, useState } from 'react';

import api from './api';
import Device from './entities/Device';
import Loading from './components/Loading/Loading';
import Authorization from './components/Authorization/Authorization';
import Search from './components/Search/Search';
import './App.scss';

export type TAppState = 'auth' | 'non_auth' | 'updating' | 'error';
export type TSetAppState = React.Dispatch<React.SetStateAction<TAppState>>;

const useApp = () => {
  const [appState, setAppState] = useState<TAppState>('updating');
  const searchData = useRef({});

  useEffect(() => {
    const token = localStorage.getItem('token');

    if (!token) {
      setAppState('non_auth');
      return;
    }

    let isIgnoreFetch = false;
    api
      .getDevices()
      .then((data) => {
        if (isIgnoreFetch) return;
        searchData.current = { devices: Device.checkArray(data) };
        setAppState('auth');
      })
      .catch((error) => {
        if (isIgnoreFetch) return;
        setAppState('error');
        console.error(error);
      });

    return () => {
      isIgnoreFetch = true;
    };
  });

  return { appState, setAppState, searchData };
};

const AppView = (props: ReturnType<typeof useApp>) => {
  return (
    <>
      <header className="header">
        <h1 className="header__h1">SoyzVape девайсы</h1>
      </header>
      <main className="main">
        {props.appState === 'auth' ? (
          <Search searchData={props.searchData} />
        ) : props.appState === 'non_auth' ? (
          <Authorization setAppState={props.setAppState} />
        ) : props.appState === 'updating' ? (
          <Loading />
        ) : (
          <h2>Упс... что-то пошло не так...</h2>
        )}
      </main>
    </>
  );
};

export default function App() {
  return <AppView {...useApp()} />;
}
