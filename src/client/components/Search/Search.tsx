import cn from 'classnames';
import style from './Search.scss';

const useSearch = () => {
  return {};
};
const SearchView = (props) => {
  return (
    <>
      <h2>Поиск девайса:</h2>
      <input type="text" />
    </>
  );
};
const Search = (props) => {
  const data = useSearch();

  return <SearchView {...props} {...data} />;
};
export default Search;
