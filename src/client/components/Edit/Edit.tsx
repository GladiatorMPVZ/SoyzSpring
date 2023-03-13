import { MouseEvent, useRef, useState } from 'react';

import api from '../../api';
import utils, { TSetState } from '../../Utils';
import { TAppState } from '../../App';
import Search, { TSearchData } from '../Search/Search';
import SearchResult from '../../entities/SearchResult';
import Device from '../../entities/Device';
import Vaporizer from '../../entities/Vaporizer';
import './Edit.scss';

type TTab = 'devices' | 'vaporizers' | 'relations';

const useEdit = (props: Parameters<typeof Edit>[0]) => {
  const [tab, setTab] = useState<TTab>('devices');
  const [list, setList] = useState<SearchResult[]>(props.searchData.devices);
  const inputRef = useRef({} as HTMLInputElement);

  const updateList = () => {
    const input = inputRef.current.value;
    const dataType = tab === 'relations' ? 'devices' : tab;

    if (!input) return setList(props.searchData[dataType]);

    const bestMatches = utils.findBestMatches(input, { [tab]: props.searchData[dataType] }, 20);
    const newList = bestMatches.map((item) => new SearchResult(item.id, item.title));
    setList(newList);
  };

  const addItem = async () => {
    try {
      if (tab === 'devices') {
        await api.addDevice({ title: inputRef.current.value });
        props.searchData.devices = Device.checkArray(await api.getDevices());
      } else if (tab === 'vaporizers') {
        await api.addVaporizer({ title: inputRef.current.value });
        props.searchData = {
          devices: props.searchData.devices,
          vaporizers: Vaporizer.checkArray(await api.getVaporizers()),
        };
      }
      updateList();
    } catch (error) {
      console.error(error);
    }
  };

  const deleteItem = async (event: MouseEvent) => {
    const id = Number((event.currentTarget as HTMLElement).dataset.id as string);
    try {
      if (tab === 'devices') {
        await api.deleteDevice(id);
        props.searchData.devices = props.searchData.devices.filter((device) => device.id !== id);
      } else if (tab === 'vaporizers') {
        await api.deleteVaporizer(id);
        props.searchData.vaporizers = props.searchData.vaporizers.filter((vaporizer) => vaporizer.id !== id);
      }
      updateList();
    } catch (error) {
      console.error(error);
    }
  };

  const toDevices = () => {
    setTab('devices');
    setList(props.searchData.devices);
  };

  const toVaporizers = () => {
    setTab('vaporizers');
    setList(props.searchData.vaporizers);
  };

  const toRelations = () => {
    setTab('relations');
    setList([]);
  };

  return { ...props, tab, toDevices, toVaporizers, toRelations, inputRef, updateList, list, addItem, deleteItem };
};

const EditView = (props: ReturnType<typeof useEdit>) => {
  return (
    <div className="edit">
      <h2>Редактирование</h2>
      <div className="edit__nav">
        <button className="edit__button" type="button" onClick={props.toDevices}>
          Девайсы
        </button>
        <button className="edit__button" type="button" onClick={props.toVaporizers}>
          Испарители
        </button>
        <button className="edit__button" type="button" onClick={props.toRelations}>
          Связи
        </button>
      </div>
      {props.tab !== 'relations' ? (
        <>
          <div className="edit__search">
            <input className="search__input" type="search" ref={props.inputRef} onInput={props.updateList} />
            <button className="edit__button_add" onClick={props.addItem}>
              +
            </button>
          </div>
          <ul className="search__results">
            {props.list.map((item, i) => (
              <li className="search__result_edit" key={i}>
                <span className="search__result__id">{item.id}</span>
                <span className="search__result__title">{item.title}</span>
                <button className="edit__button__delete" data-id={item.id} onClick={props.deleteItem}>
                  -
                </button>
              </li>
            ))}
          </ul>
        </>
      ) : (
        <Search searchData={props.searchData} setAppState={props.setAppState} isEdit={true} />
      )}
    </div>
  );
};

export default function Edit(props: { searchData: TSearchData; setAppState: TSetState<TAppState> }) {
  return <EditView {...useEdit(props)} />;
}
