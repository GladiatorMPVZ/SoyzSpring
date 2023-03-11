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

const baseUrl = 'http://192.168.1.104:8100/soyz-shop/api/v1/';

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
  const url = baseUrl + 'registration';

  return fetchJSON('POST', url, dto);
};

const authorize = (dto: AuthDTO) => {
  const url = baseUrl + 'auth';

  return fetchJSON('POST', url, dto);
};

const getDevices = () => {
  const url = baseUrl + 'devices';

  return fetchJSON('GET', url);
};

const getDeviceById = (id: number) => {
  const url = baseUrl + `devices/${id}`;

  return fetchJSON('GET', url);
};

const getDevicesByVaporizerName = (vaporizerTitle: string) => {
  const url = baseUrl + `devices/filtered?vaporizerTitle=${vaporizerTitle}`;

  return fetchJSON('GET', url);
};

const addDevice = (dto: AddEntityDTO) => {
  const url = baseUrl + 'devices';

  return fetchJSON('POST', url, dto);
};

const deleteDeviceById = (id: number) => {
  const url = baseUrl + `devices/${id}`;

  return fetchJSON('DELETE', url);
};

const getVaporizers = () => {
  const url = baseUrl + 'vaporizers';

  return fetchJSON('GET', url);
};

const getVaporizerById = (id: number) => {
  const url = baseUrl + `vaporizers/${id}`;

  return fetchJSON('GET', url);
};

const getVaporizersByDeviceName = (deviceTitle: string) => {
  const url = baseUrl + `vaporizers/filtered?deviceTitle=${deviceTitle}`;

  return fetchJSON('GET', url);
};

const addVaporizer = (dto: AddEntityDTO) => {
  const url = baseUrl + 'vaporizers';

  return fetchJSON('POST', url, dto);
};

const deleteVaporizerById = (id: number) => {
  const url = baseUrl + `vaporizers/${id}`;

  return fetchJSON('DELETE', url);
};

const getBoxesByDeviceName = (deviceTitle: string) => {
  const url = baseUrl + `boxes?deviceTitle=${deviceTitle}`;

  return fetchJSON('GET', url);
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
  getBoxesByDeviceName,
};
