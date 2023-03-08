import { distance } from 'fastest-levenshtein';
import { TSearchData } from './components/Search/Search';

export type TBestMatches = {
  id: number;
  title: string;
  rating: number;
  type: string;
};

const findBestMatches = (sample: string, searchData: TSearchData, size = 5) => {
  const bestMatches = new Array<TBestMatches>(size).fill({ id: 0, title: '', rating: 0, type: 'device' });
  sample = sample.toLowerCase();

  for (const [key, entities] of Object.entries(searchData))
    for (const entity of entities) {
      const id = entity.id;
      const title = entity.title.toLowerCase();
      const longer = Math.max(sample.length, title.length);
      const rating = (longer - distance(sample, title)) / longer;
      let index: number;
      index = bestMatches.findIndex((c) => c.rating === 0);
      if (index === -1) index = bestMatches.findIndex((c) => c.rating < rating);
      if (index !== -1) bestMatches.splice(index, 1, { id, title, rating, type: key });
    }

  return bestMatches.filter((c) => c.rating !== 0).sort((a, b) => b.rating - a.rating);
};

export default {
  findBestMatches,
};
