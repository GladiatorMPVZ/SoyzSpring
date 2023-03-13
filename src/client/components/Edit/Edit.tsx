import { useRef, useState } from 'react';

import utils, { TSetState } from '../../Utils';
import { TAppState } from '../../App';
import { TSearchData } from '../Search/Search';
import SearchResult from '../../entities/SearchResult';
import './Edit.scss';

type TTab = 'devices' | 'vaporizers';

const useEdit = (props: Parameters<typeof Edit>[0]) => {
  const [tab, setTab] = useState<TTab>('devices');
  const [list, setList] = useState<SearchResult[]>(props.searchData.devices);
  const inputRef = useRef({} as HTMLInputElement);

  const updateList = () => {
    const input = inputRef.current.value;

    if (!input) return setList(props.searchData[tab]);

    const bestMatches = utils.findBestMatches(input, { [tab]: props.searchData[tab] }, 20);
    const newList = bestMatches.map((item) => new SearchResult(item.id, item.title));
    setList(newList);
  };
  const toDevices = () => {
    setTab('devices');
    setList(props.searchData.devices);
  };
  const toVaporizers = () => {
    setTab('vaporizers');
    setList(props.searchData.vaporizers);
  };

  return { toDevices, toVaporizers, inputRef, updateList, list };
};

const EditView = (props: ReturnType<typeof useEdit>) => {
  return (
    <>
      <h2>Редактирование</h2>
      <button className="edit__button" type="button" onClick={props.toDevices}>
        Девайсы
      </button>
      <button className="edit__button" type="button" onClick={props.toVaporizers}>
        Испарители
      </button>
      <input className="edit__input" ref={props.inputRef} type="search" onInput={props.updateList} />
      <ul className="edit__list">
        {props.list.map((item, i) => (
          <li key={i}>
            <span className="edit__list__id">{item.id}</span>
            <span className="edit__list__title">{item.title}</span>
            {item.boxNumber && <span className="edit__list__box-number">{item.boxNumber}</span>}
          </li>
        ))}
      </ul>
    </>
  );
};

export default function Edit(props: { searchData: TSearchData; setAppState: TSetState<TAppState> }) {
  return <EditView {...useEdit(props)} />;
}
