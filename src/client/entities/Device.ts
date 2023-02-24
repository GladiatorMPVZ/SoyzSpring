const isDevice = (data: unknown): data is Device =>
  typeof (data as Device)?.id === 'number' && typeof (data as Device)?.title === 'string';
const isDeviceArray = (data: unknown): data is Device[] => data instanceof Array && isDevice(data[0]);

export default class Device {
  id: number;
  title: string;

  constructor(id: number, title: string) {
    this.id = id;
    this.title = title;
  }

  static checkArray = (data: unknown) => {
    if (isDeviceArray(data)) return data;
    throw new Error(`No devices, received data: ${data})`);
  };
}
