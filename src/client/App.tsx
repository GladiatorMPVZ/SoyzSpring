import { useState } from 'react';

import Loading from './components/Loading/Loading';
import Authorization from './components/Authorization/Authorization';
import Search from './components/Search/Search';
import './App.scss';

type AuthStatus = 'auth' | 'non_auth' | 'updating' | 'error';
const useApp = () => {
  const [Auth, setAuth] = useState<AuthStatus>('updating');
  return { Auth, setAuth };
};
const AppView = (props: { Auth: AuthStatus }) => {
  return (
    <>
      <header className="header">
        <h1>SoyzVape девайсы</h1>
      </header>
      <main className="main">
        {props.Auth === 'auth' ? (
          <Search />
        ) : props.Auth === 'non_auth' ? (
          <Authorization />
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
