const isSearchResult = (data: unknown): data is SearchResult => typeof (data as SearchResult)?.title === 'string';

const isSearchResults = (data: unknown): data is SearchResult[] => data instanceof Array && isSearchResult(data[0]);

export default class SearchResult {
  constructor(public id: number, public title: string, public boxNumber?: number) {
    this.id = id;
    this.title = title;
    this.boxNumber = boxNumber;
  }

  static check = (data: unknown) => {
    if (isSearchResults(data)) return data;
    throw new Error(`No results, received data: ${data}`);
  };
}
