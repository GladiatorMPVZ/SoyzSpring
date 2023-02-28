import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.scss';

const token = localStorage.getItem('token');

ReactDOM.createRoot(document.getElementById('app') as HTMLElement).render(
  <React.StrictMode>
    <App initState={token ? 'updating' : 'non_auth'} />
  </React.StrictMode>,
);
