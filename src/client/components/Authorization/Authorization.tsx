import { useRef, useState } from 'react';
import cn from 'classnames';

import api from '../../api';
import { TSetAppState } from '../../App';
import './Authorization.scss';

type TAuth = 'idle' | 'error';

type Token = {
  token: string;
};

const isToken = (data: unknown): data is Token => typeof (data as Token)?.token === 'string';

const checkToken = (data: unknown) => {
  if (isToken(data)) return data;
  throw new Error(`No token, received data: ${data}`);
};

const useAuthorization = (props: { setAppState: TSetAppState }) => {
  const [isSignIn, setSignIn] = useState(true);
  const [auth, setAuth] = useState<TAuth>('idle');
  const userNameRef = useRef(null);
  const passwordRef = useRef(null);
  const confirmPasswordRef = useRef(null);
  const errorMessage = useRef('');
  const action = async () => {
    const username = (userNameRef.current as unknown as HTMLInputElement).value;
    const password = (passwordRef.current as unknown as HTMLInputElement).value;

    try {
      if (isSignIn) {
        const data = await api.authorize({ username, password });
        const token = checkToken(data).token;
        localStorage.setItem('token', token);
        props.setAppState('updating');
      } else {
        const confirmPassword = (confirmPasswordRef.current as unknown as HTMLInputElement).value;
        await api.register({ username, password, confirmPassword });
      }
    } catch (error) {
      const err = error as { message: string; code: number };
      if (err?.code === 400 || err?.code === 401) {
        errorMessage.current = err?.message;
        setAuth('error');
        console.error(error);
      }
    }
  };

  return { isSignIn, setSignIn, userNameRef, passwordRef, confirmPasswordRef, action, auth, errorMessage };
};

const AuthorizationView = (props: ReturnType<typeof useAuthorization>) => {
  return (
    <form className="auth__form">
      <button
        className={cn('auth__button_set-signin', { auth__button_active: props.isSignIn })}
        type="button"
        onClick={() => props.setSignIn(true)}
      >
        Вход
      </button>
      <button
        className={cn('auth__button_set-signup', { auth__button_active: !props.isSignIn })}
        type="button"
        onClick={() => props.setSignIn(false)}
      >
        Регистрация
      </button>
      <h2 className="auth__h2">{props.isSignIn ? 'Вход' : 'Регистрация'}</h2>
      {props.auth && <span className="auth__error">{props.errorMessage.current}</span>}
      <input className="auth__username" ref={props.userNameRef} type="text" placeholder="Логин" />
      <input className="auth__password" ref={props.passwordRef} type="text" placeholder="Пароль" />
      {!props.isSignIn && (
        <input
          className="auth__confirm-password"
          ref={props.confirmPasswordRef}
          type="text"
          placeholder="Подтверждение пароля"
        />
      )}
      <button className="auth__button_action" type="button" onClick={() => props.action()}>
        {props.isSignIn ? 'Войти' : 'Зарегистрироваться'}
      </button>
    </form>
  );
};

export default function Authorization(props: { setAppState: TSetAppState }) {
  return <AuthorizationView {...useAuthorization(props)} />;
}
