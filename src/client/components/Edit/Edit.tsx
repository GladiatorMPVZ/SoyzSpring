import React from 'react';
import { TSetState } from '../../Utils';
import { TAppState } from '../../App';
import { TSearchData } from '../Search/Search';

const useEdit = (props: Parameters<typeof Edit>[0]) => {
  return {};
};

const EditView = (props: ReturnType<typeof useEdit>) => {
  return <></>;
};

export default function Edit(props: { searchData: TSearchData; setAppState: TSetState<TAppState> }) {
  return <EditView {...useEdit(props)} />;
}
