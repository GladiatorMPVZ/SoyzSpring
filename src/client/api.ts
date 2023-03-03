export type AuthDTO = {
  username: string;
  password: string;
};

export type RegisterDTO = AuthDTO & {
  confirmPassword: string;
};

export type AddEntityDTO = {
  title: string;
};

type FetchData = AuthDTO | RegisterDTO | AddEntityDTO;

const fetchJSON = async (method: string, url: string, data?: FetchData) => {
  const token = localStorage.getItem('token');
  const options = {
    method,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json;charset=UTF-8',
      ...(token ? { authorization: `Bearer ${token}` } : {}),
    },
    body: data && JSON.stringify(data),
  };
  const res = await fetch(url, options);
  if (res.status >= 400) throw { message: (await res.json().catch(() => ({})))?.message, code: res.status };
  return await (res.json().catch(() => ({})) as Promise<unknown>);
};

const register = (dto: RegisterDTO) => {
  const url = 'api/v1/registration';

  return fetchJSON('POST', url, dto);
};

const authorize = (dto: AuthDTO) => {
  const url = 'api/v1/auth';

  return fetchJSON('POST', url, dto);
};

const getDevices = () => {
  const url = 'api/v1/devices';

  return fetchJSON('GET', url);
};

const getDeviceById = (id: number) => {
  const url = `api/v1/devices/${id}`;

  return fetchJSON('GET', url);
};

const getDevicesByVaporizerName = (vaporizerTitle: string) => {
  const url = `api/v1/devices/filtered?vaporizerTitle=${vaporizerTitle}`;

  return fetchJSON('GET', url);
};

const addDevice = (dto: AddEntityDTO) => {
  const url = 'api/v1/devices/';

  return fetchJSON('POST', url, dto);
};

const deleteDeviceById = (id: number) => {
  const url = `api/v1/devices/${id}`;

  return fetchJSON('DELETE', url);
};

const getVaporizers = () => {
  const url = 'api/v1/vaporizers';

  return fetchJSON('GET', url);
};

const getVaporizerById = (id: number) => {
  const url = `api/v1/vaporizers/${id}`;

  return fetchJSON('GET', url);
};

const getVaporizersByDeviceName = (deviceTitle: string) => {
  const url = `api/v1/vaporizers/filtered?deviceTitle=${deviceTitle}`;

  return fetchJSON('GET', url);
};

const addVaporizer = (dto: AddEntityDTO) => {
  const url = 'api/v1/vaporizers/';

  return fetchJSON('POST', url, dto);
};

const deleteVaporizerById = (id: number) => {
  const url = `api/v1/vaporizers/${id}`;

  return fetchJSON('DELETE', url);
};

export default {
  register,
  authorize,
  getDevices,
  getDeviceById,
  getDevicesByVaporizerName,
  addDevice,
  deleteDeviceById,
  getVaporizers,
  getVaporizerById,
  getVaporizersByDeviceName,
  addVaporizer,
  deleteVaporizerById,
};
