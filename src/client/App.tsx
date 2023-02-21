import { useEffect, useRef, useState } from 'react';

import api from './api';
import Loading from './components/Loading/Loading';
import Authorization from './components/Authorization/Authorization';
import Search from './components/Search/Search';
import './App.scss';

type AuthStatus = 'auth' | 'non_auth' | 'updating' | 'error';
type Device = {
  id: number;
  title: string;
};

const useApp = () => {
  const [Auth, setAuth] = useState<AuthStatus>('updating');
  const searchData = useRef({});

  useEffect(() => {
    const token = localStorage.getItem('token');

    if (!token) {
      setAuth('non_auth');
      return;
    }
    api
      .getDevices()
      .then((devices: Array<Device>) => {
        if (!devices || !devices.length) {
          setAuth('error');
          console.error('No devices:', devices);
        } else {
          setAuth('auth');
          searchData.current = { devices };
        }
      })
      .catch((error) => {
        setAuth('error');
        console.error(error);
      });
  });

  return { Auth, setAuth, searchData };
};
const AppView = (props: ReturnType<typeof useApp>) => {
  return (
    <>
      <header className="header">
        <h1>SoyzVape девайсы</h1>
      </header>
      <main className="main">
        {props.Auth === 'auth' ? (
          <Search searchData={props.searchData} />
        ) : props.Auth === 'non_auth' ? (
          <Authorization setAuth={props.setAuth} />
        ) : props.Auth === 'updating' ? (
          <Loading />
        ) : (
          <p>Ups</p>
        )}
      </main>
    </>
  );
};
const App = () => {
  const data = useApp();

  return <AppView {...data} />;
};

export default App;
