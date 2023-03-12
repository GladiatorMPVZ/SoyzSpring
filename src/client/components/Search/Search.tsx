import React, { FormEvent, useCallback, useEffect, useRef, useState } from 'react';
import cn from 'classnames';

import api from '../../api';
import utils, { TBestMatch, TSetState } from '../../Utils';
import { TAppState } from '../../App';
import Device from '../../entities/Device';
import Vaporizer from '../../entities/Vaporizer';
import SearchResult from '../../entities/SearchResult';
import './Search.scss';

export type TSearchData = {
  devices: Device[];
  vaporizers: Vaporizer[];
};

const useSearch = (props: Parameters<typeof Search>[0]) => {
  const [searchInput, setSearchInput] = useState('');
  const [searchResult, setSearchResult] = useState<SearchResult[]>([]);
  const [bestMatches, setBestMatches] = useState<TBestMatch[]>([]);
  const [isShowOptions, setShowOptions] = useState(false);
  const optionsRef = useRef({} as HTMLUListElement);

  const updateOptions = (event: FormEvent<HTMLInputElement>) => {
    const input = (event.target as HTMLInputElement).value;
    const bestMatches = utils.findBestMatches(input, props.searchData);
    setBestMatches(bestMatches);
    setSearchInput(input);
    setShowOptions(!!bestMatches.length);
  };

  const search = useCallback(async (event: MouseEvent) => {
    const option = (event.target as HTMLElement).closest('.search__option') as HTMLElement;
    const type = option.dataset.type;
    const title = option.dataset.title as string;
    const id = option.dataset.id as string;
    let data: unknown;

    try {
      if (type === 'devices') data = await api.getBoxesByDeviceId(id);
      else data = await api.getDevicesByVaporizerId(id);

      setSearchInput(title);
      setShowOptions(false);
      if (!(data instanceof Array && data.length)) return setSearchResult([]);
      setSearchResult(SearchResult.check(data));
    } catch (error) {
      console.error(error);
      props.setAppState('error');
    }
  }, []);

  useEffect(() => {
    optionsRef.current.addEventListener('click', search);
  });

  return { searchInput, updateOptions, bestMatches, optionsRef, isShowOptions, searchResult };
};

const SearchView = (props: ReturnType<typeof useSearch>) => {
  return (
    <div className="search">
      <h2 className="search__title">Поиск подходящих друг к другу устройств</h2>
      <div className="search__bar">
        <input className="search__input" type="search" value={props.searchInput} onInput={props.updateOptions} />
        <ul className={cn('search__options', { search__options_show: props.isShowOptions })} ref={props.optionsRef}>
          {props.bestMatches.map(
            (match, i) =>
              !!match.rating && (
                <li
                  className="search__option"
                  key={i}
                  data-id={`${match.id}`}
                  data-title={match.title}
                  data-type={match.type}
                >
                  <span>{match.title}</span>
                  <span className="search__option__rating">{`${(match.rating * 100).toFixed(2)}%`}</span>
                  <span className="search__option__type">{match.type === 'devices' ? 'Девайс' : 'Испаритель'}</span>
                </li>
              ),
          )}
        </ul>
      </div>
      <ul className="search__results">
        {props.searchResult.map((result, i) => (
          <li className="search__result" key={i}>
            <span>{result.title}</span>
            {result.boxNumber && <span>Коробка № {result.boxNumber}</span>}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default function Search(props: { searchData: TSearchData; setAppState: TSetState<TAppState> }) {
  return <SearchView {...useSearch(props)} />;
}
