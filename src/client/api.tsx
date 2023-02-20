const fetchJSON = (method, url, data?) => {
  const options = {
    method,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json;charset=UTF-8',
    },
    body: data ? JSON.stringify(data) : '',
  };
  return fetch(url, options).then((res) => res.json());
};

const register = (username, password, confirmPassword) => {
  const url = 'api/v1/registration';

  return fetchJSON('POST', url, { username, password, confirmPassword });
};
const authorize = (username, password) => {
  const url = 'api/v1/auth';

  return fetchJSON('POST', url, { username, password });
};

const getDevices = () => {
  const url = 'api/v1/devices';

  return fetchJSON('GET', url);
};

const getDeviceById = (id) => {
  const url = `api/v1/devices/${id}`;

  return fetchJSON('GET', url);
};

const getDeviceByVaporizer = (vaporizerTitle) => {
  const url = `api/v1/devices/filtered?vaporizerTitle=${vaporizerTitle}`;

  return fetchJSON('GET', url);
};

const addDevice = (title) => {
  const url = 'api/v1/devices/';

  return fetchJSON('POST', url, { title });
};

const deleteDeviceById = (id) => {
  const url = `api/v1/devices/${id}`;

  return fetchJSON('DELETE', url);
};

export default {
  register,
  authorize,
  getDevices,
  getDeviceById,
  getDeviceByVaporizer,
  addDevice,
  deleteDeviceById,
};
