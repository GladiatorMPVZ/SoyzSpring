import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import utils from './Utils';
import './index.scss';

const token = localStorage.getItem('token');
const role = utils.getRole(token);

ReactDOM.createRoot(document.getElementById('app') as HTMLElement).render(
  <React.StrictMode>
    <App initPage={token ? 'Loading' : 'Auth'} initState={token ? 'auth' : 'non_auth'} userRole={role} />
  </React.StrictMode>,
);
