import { TSetState } from '../../Utils';
import { TAppState, TPage, TRole } from '../../App';
import './Nav.scss';

const useNav = (props: Parameters<typeof Nav>[0]) => {
  const toAuth = () => {
    props.setPage('Auth');
  };

  const toSearch = () => {
    props.setPage('Search');
  };

  const toEdit = () => {
    props.setPage('Edit');
  };
  return { ...props, toAuth, toSearch, toEdit };
};

const NavView = (props: ReturnType<typeof useNav>) => {
  return (
    <nav className="nav">
      {props.userRole === 'Admin' && (
        <button className="nav__button" type="button" onClick={props.toAuth}>
          Регистрация
        </button>
      )}
      {props.appState === 'auth' && (
        <>
          <button className="nav__button" type="button" onClick={props.toSearch}>
            Поиск
          </button>
          <button className="nav__button" type="button" onClick={props.toEdit}>
            Редактирование
          </button>
          <button className="nav__button_logout" type="button" onClick={props.logOut}>
            Выйти
          </button>
        </>
      )}
    </nav>
  );
};

export default function Nav(props: {
  setPage: TSetState<TPage>;
  appState: TAppState;
  userRole: TRole;
  logOut: () => void;
}) {
  return <NavView {...useNav(props)} />;
}
