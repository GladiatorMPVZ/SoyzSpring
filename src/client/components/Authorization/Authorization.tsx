import { useRef, useState } from 'react';
import cn from 'classnames';

import api from '../../api';
import utils, { TSetState } from '../../Utils';
import { TAppState, TPage, TRole } from '../../App';
import './Authorization.scss';

type Token = {
  token: string;
};

const isToken = (data: unknown): data is Token => typeof (data as Token)?.token === 'string';

const checkToken = (data: unknown) => {
  if (isToken(data)) return data;
  throw new Error(`No token, received data: ${data}`);
};

const useAuthorization = (props: Parameters<typeof Authorization>[0]) => {
  const [isSignIn, setSignIn] = useState(props.appState !== 'auth');
  const [isError, setIsError] = useState(false);
  const [message, setMessage] = useState('');
  const userNameRef = useRef(null);
  const passwordRef = useRef(null);
  const confirmPasswordRef = useRef(null);

  const action = async () => {
    const username = (userNameRef.current as unknown as HTMLInputElement).value;
    const password = (passwordRef.current as unknown as HTMLInputElement).value;

    try {
      if (props.appState !== 'auth') {
        const data = await api.authorize({ username, password });
        const token = checkToken(data).token;
        localStorage.setItem('token', token);
        props.setPage('Search');
        props.setUserRole(utils.getRole(token));
        props.setAppState('auth');
      } else {
        const confirmPassword = (confirmPasswordRef.current as unknown as HTMLInputElement).value;
        await api.register({ username, password, confirmPassword });
        setMessage('Успешная регистрация!');
      }
      setIsError(false);
    } catch (error) {
      const err = error as { message: string; code: number };
      console.error(error);
      if (err?.code === 400 || err?.code === 401) {
        setMessage(err?.message);
        setIsError(true);
      } else {
        props.setAppState('error');
      }
    }
  };

  const toSignIn = () => setSignIn(true);
  const toSignUp = () => setSignIn(false);

  return {
    isSignIn,
    userNameRef,
    passwordRef,
    confirmPasswordRef,
    action,
    isError,
    message,
    toSignIn,
    toSignUp,
    userRole: props.userRole,
    appState: props.appState,
  };
};

const AuthorizationView = (props: ReturnType<typeof useAuthorization>) => {
  return (
    <form className="auth__form">
      {props.appState === 'error' && (
        <>
          <button
            className={cn('auth__button_set-signin', { auth__button_active: props.isSignIn })}
            type="button"
            onClick={props.toSignIn}
          >
            Вход
          </button>
          <button
            className={cn('auth__button_set-signup', { auth__button_active: !props.isSignIn })}
            type="button"
            onClick={props.toSignUp}
          >
            Регистрация
          </button>
        </>
      )}
      <h2 className="auth__h2">{props.appState !== 'auth' ? 'Вход' : 'Регистрация'}</h2>
      <span className={cn('auth__msg', { auth__msg_error: props.isError })}>{props.message}</span>
      <input className="auth__username" ref={props.userNameRef} type="text" placeholder="Логин" />
      <input className="auth__password" ref={props.passwordRef} type="password" placeholder="Пароль" />
      {props.appState === 'auth' && (
        <input
          className="auth__confirm-password"
          ref={props.confirmPasswordRef}
          type="password"
          placeholder="Подтверждение пароля"
        />
      )}
      <button className="auth__button_action" type="button" onClick={props.action}>
        {props.appState !== 'auth' ? 'Войти' : 'Зарегистрироваться'}
      </button>
    </form>
  );
};

export default function Authorization(props: {
  setPage: TSetState<TPage>;
  appState: TAppState;
  setAppState: TSetState<TAppState>;
  userRole: TRole;
  setUserRole: TSetState<TRole>;
}) {
  return <AuthorizationView {...useAuthorization(props)} />;
}
