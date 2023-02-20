const fetchJSON = (method, url, data) => {
  const options = {
    method,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json;charset=UTF-8',
    },
    body: JSON.stringify(data),
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

export default {
  register,
  authorize,
};
