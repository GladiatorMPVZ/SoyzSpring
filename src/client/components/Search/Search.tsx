import React, { useRef, useState } from 'react';

import utils, { TBestMatches } from '../../Utils';
import Device from '../../entities/Device';
import Vaporizer from '../../entities/Vaporizer';
import './Search.scss';

export type TSearchData = {
  devices: Device[];
  vaporizers: Vaporizer[];
};

const useSearch = (searchData: TSearchData) => {
  const searchRef = useRef({} as HTMLInputElement);
  const [bestMatches, setBestMatches] = useState(Array<TBestMatches>);
  console.log(bestMatches);
  const search = () => {
    const input = searchRef.current.value;
    setBestMatches(utils.findBestMatches(input, searchData));
  };

  return { searchRef, search, bestMatches };
};
const SearchView = (props: ReturnType<typeof useSearch>) => {
  return (
    <>
      <h2>Поиск девайса:</h2>
      <input className="search__input" type="text" ref={props.searchRef} onInput={() => props.search()} />
      {props.bestMatches.map(
        (c, i) =>
          !!c.rating && <button key={i} type="button">{`${c.title}        ${c.rating}         ${c.type}`}</button>,
      )}
    </>
  );
};
export default function Search(props: { searchData: TSearchData }) {
  return <SearchView {...useSearch(props.searchData)} />;
}
