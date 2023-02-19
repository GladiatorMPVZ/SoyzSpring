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
const Search = () => {
  const props = useSearch();

  return <SearchView {...props} />;
};
export default Search;
