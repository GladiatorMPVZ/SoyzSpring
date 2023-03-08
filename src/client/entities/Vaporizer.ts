const isVaporizer = (data: unknown, withId = true): data is Vaporizer =>
  withId
    ? typeof (data as Vaporizer)?.id === 'number' && typeof (data as Vaporizer)?.title === 'string'
    : typeof (data as Vaporizer)?.deviceTitle === 'string';

const isVaporizerArray = (data: unknown, withId = true): data is Vaporizer[] =>
  data instanceof Array && isVaporizer(data[0]);

export default class Vaporizer {
  constructor(public id: number, public title?: string, public deviceTitle?: string) {
    this.id = id;
    this.title = title;
  }

  static checkArray = (data: unknown, withId = true) => {
    if (isVaporizerArray(data, withId)) return data;
    throw new Error(`No vaporizers, received data: ${data}`);
  };
}
