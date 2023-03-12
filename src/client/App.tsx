import React, { useEffect, useRef, useState } from 'react';

import api from './api';
import Device from './entities/Device';
import Vaporizer from './entities/Vaporizer';
import Loading from './components/Loading/Loading';
import Authorization from './components/Authorization/Authorization';
import Search, { TSearchData } from './components/Search/Search';
import Edit from './components/Edit/Edit';
import './App.scss';

export type TAppState = 'auth' | 'non_auth' | 'error';
export type TRole = 'Admin' | 'User' | 'Guest';
export type TPage = 'Auth' | 'Search' | 'Edit' | 'Loading' | 'Error';

const useApp = (props: Parameters<typeof App>[0]) => {
  const [page, setPage] = useState<TPage>(props.initPage);
  const [appState, setAppState] = useState<TAppState>(props.initState);
  const [userRole, setUserRole] = useState<TRole>(props.userRole);
  const searchData = useRef({} as TSearchData);

  useEffect(() => {
    if (page !== 'Loading') return;

    let isIgnoreFetch = false;
    (async function () {
      try {
        const dataDevices = api.getDevices();
        const dataVaporizers = api.getVaporizers();
        if (isIgnoreFetch) return;
        searchData.current = {
          devices: Device.checkArray(await dataDevices),
          vaporizers: Vaporizer.checkArray(await dataVaporizers),
        };
        setPage('Search');
      } catch (error) {
        if (isIgnoreFetch) return;
        setAppState('error');
        setPage('Error');
        console.error(error);
      }
    })();
    return () => {
      isIgnoreFetch = true;
    };
  });

  const logOut = () => {
    localStorage.removeItem('token');
    setAppState('non_auth');
    setPage('Auth');
    setUserRole('Guest');
  };

  const toAuth = () => {
    setPage('Auth');
  };

  const toApp = () => {
    setPage('Search');
  };

  return {
    appState,
    setAppState,
    searchData: searchData.current,
    logOut,
    userRole,
    setUserRole,
    toAuth,
    toApp,
    page,
    setPage,
  };
};

const AppView = (props: ReturnType<typeof useApp>) => {
  return (
    <>
      <header className="header">
        <h1 className="header__h1">SoyzVape</h1>
        {props.userRole === 'Admin' && props.page !== 'Auth' && (
          <button className="header__button-auth" type="button" onClick={props.toAuth}>
            Регистрация
          </button>
        )}
        {props.userRole === 'Admin' && props.page === 'Auth' && props.appState === 'auth' && (
          <button className="header__button-auth" type="button" onClick={props.toApp}>
            Поиск
          </button>
        )}
        {props.appState === 'auth' && (
          <button className="header__button-logout" type="button" onClick={props.logOut}>
            Выйти
          </button>
        )}
      </header>
      <main className="main">
        {props.page === 'Search' ? (
          <Search searchData={props.searchData} setAppState={props.setAppState} />
        ) : props.page === 'Auth' ? (
          <Authorization
            setPage={props.setPage}
            appState={props.appState}
            setAppState={props.setAppState}
            userRole={props.userRole}
            setUserRole={props.setUserRole}
          />
        ) : props.page === 'Edit' ? (
          <Edit searchData={props.searchData} setAppState={props.setAppState} />
        ) : props.page === 'Loading' ? (
          <Loading />
        ) : (
          <h2>Упс... что-то пошло не так...</h2>
        )}
      </main>
    </>
  );
};

export default function App(props: { initState: TAppState; userRole: TRole; initPage: TPage }) {
  return <AppView {...useApp(props)} />;
}
