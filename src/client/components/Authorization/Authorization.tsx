import cn from 'classnames';
import style from './Authorization.scss';

const useAuthorization = () => {
  return {};
};
const AuthorizationView = (props) => {
  return (
    <>
      <h2>Авторизация</h2>
      <input type="text" placeholder="Логин" />
      <input type="text" placeholder="Пароль" />
    </>
  );
};
const Authorization = (props) => {
  const data = useAuthorization();

  return <AuthorizationView {...props} {...data} />;
};
export default Authorization;
