import './Loading.scss';
import soyzLogo from './soyzLogo.webp';

const LoadingView = () => {
  return <img className="loading__img" src={soyzLogo} alt="Soyz logo" />;
};

export default function Loading() {
  return <LoadingView />;
}
