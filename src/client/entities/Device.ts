const isDevice = (data: unknown, withId = true): data is Device =>
  withId
    ? typeof (data as Device)?.id === 'number' && typeof (data as Device)?.title === 'string'
    : typeof (data as Device)?.deviceTitle === 'string';

const isDeviceArray = (data: unknown, withId = true): data is Device[] =>
  data instanceof Array && isDevice(data[0], withId);

export default class Device {
  constructor(public id: number, public title: string, public deviceTitle?: string) {
    this.id = id;
    this.title = title;
  }

  static checkArray = (data: unknown, withId = true) => {
    if (isDeviceArray(data, withId)) return data;
    throw new Error(`No devices, received data: ${data}`);
  };
}
