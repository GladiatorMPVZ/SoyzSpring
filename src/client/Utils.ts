import { compareTwoStrings } from 'string-similarity';
import { TSearchData } from './components/Search/Search';

export type TBestMatches = {
  id: number;
  title: string;
  rating: number;
  type: 'device' | 'vaporizer';
};

const findBestMatches = (sample: string, searchData: TSearchData, size = 3) => {
  const bestMatches = new Array<TBestMatches>(size).fill({ id: 0, title: '', rating: 0, type: 'device' });

  for (let i = 0; i < searchData.devices.length; i++) {
    const id = searchData.devices[i].id;
    const title = searchData.devices[i].title;
    const rating = compareTwoStrings(sample, title);
    const index = bestMatches.findIndex((c) => c.rating < rating);
    if (index !== -1) bestMatches.splice(index, 1, { id, title, rating, type: 'device' });
  }

  for (let i = 0; i < searchData.vaporizers.length; i++) {
    const id = searchData.vaporizers[i].id;
    const title = searchData.vaporizers[i].title;
    const rating = compareTwoStrings(sample, title);
    const index = bestMatches.findIndex((c) => c.rating < rating);
    if (index !== -1) bestMatches.splice(index, 1, { id, title, rating, type: 'vaporizer' });
  }

  return bestMatches.filter((c) => c.rating !== 0);
};

export default {
  findBestMatches,
};
