import React from 'react';
import { distance } from 'fastest-levenshtein';
import { TRole } from './App';
import { TSearchData } from './components/Search/Search';

export type TBestMatch = {
  id: number;
  title: string;
  rating: number;
  type: string;
};

const findBestMatches = (sample: string, searchData: TSearchData, size = 5) => {
  const bestMatches = new Array<TBestMatch>(size).fill({ id: 0, title: '', rating: 0, type: '' });
  sample = sample.toLowerCase();

  for (const [type, entities] of Object.entries(searchData))
    for (const entity of entities) {
      const title = entity.title;
      const longer = Math.max(sample.length, title.length);
      const rating = (longer - distance(sample, title.toLowerCase())) / longer;
      let index: number;
      index = bestMatches.findIndex((match) => match.rating === 0);
      if (index === -1) index = bestMatches.findIndex((match) => match.rating < rating);
      if (index !== -1) bestMatches.splice(index, 1, { id: entity.id, title, rating, type });
    }

  return bestMatches.filter((match) => match.rating !== 0).sort((a, b) => b.rating - a.rating);
};

type TTokenData = { sub: string; roles: TRole[] };

const getRole = (token: string | null) => {
  let role: TRole = 'Guest';
  if (token) {
    const tokenData = JSON.parse(atob(token.split('.')[1])) as TTokenData;
    role = tokenData.roles[0];
  }
  return role;
};

export type TSetState<T> = React.Dispatch<React.SetStateAction<T>>;

export default {
  findBestMatches,
  getRole,
};
