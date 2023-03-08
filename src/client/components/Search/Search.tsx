import React, { FormEvent, useCallback, useEffect, useRef, useState } from 'react';
import cn from 'classnames';

import api from '../../api';
import utils, { TBestMatches } from '../../Utils';
import { TSetAppState } from '../../App';
import Device from '../../entities/Device';
import Vaporizer from '../../entities/Vaporizer';
import './Search.scss';

export type TSearchData = {
  devices: Device[];
  vaporizers: Vaporizer[];
};

const useSearch = (props: Parameters<typeof Search>[0]) => {
  const [searchInput, setSearchInput] = useState('');
  const [searchResult, setSearchResult] = useState([] as Device[]);
  const [bestMatches, setBestMatches] = useState([] as TBestMatches[]);
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
    let data: unknown;

    try {
      if (type === 'device') data = await api.getVaporizersByDeviceName(title);
      else data = await api.getDevicesByVaporizerName(title);

      setSearchInput(title);
      setShowOptions(false);
      setSearchResult(Device.checkArray(data, false));
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
        <input
          className="search__input"
          type="text"
          value={props.searchInput}
          onInput={(event) => props.updateOptions(event)}
        />
        <ul className={cn('search__options', { search__options_show: props.isShowOptions })} ref={props.optionsRef}>
          {props.bestMatches.map(
            (c, i) =>
              !!c.rating && (
                <li className="search__option" key={i} data-type={`${c.type}`} data-title={`${c.title}`}>
                  <span>{c.title}</span>
                  <span>{`${(c.rating * 100).toFixed(2)}%`}</span>
                  <span>{c.type === 'device' ? 'Девайс' : 'Испаритель'}</span>
                </li>
              ),
          )}
        </ul>
      </div>
      <ul className="search__results">
        {props.searchResult.map((c, i) => (
          <li className="search__result" key={i}>
            {c.deviceTitle}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default function Search(props: { searchData: TSearchData; setAppState: TSetAppState }) {
  return <SearchView {...useSearch(props)} />;
}
