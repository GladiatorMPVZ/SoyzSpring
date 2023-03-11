const isVaporizer = (data: unknown): data is Vaporizer =>
  typeof (data as Vaporizer)?.id === 'number' && typeof (data as Vaporizer)?.title === 'string';

const isVaporizerArray = (data: unknown): data is Vaporizer[] => data instanceof Array && isVaporizer(data[0]);

export default class Vaporizer {
  constructor(public id: number, public title: string) {
    this.id = id;
    this.title = title;
  }

  static checkArray = (data: unknown) => {
    if (isVaporizerArray(data)) return data;
    throw new Error(`No vaporizers, received data: ${data}`);
  };
}
