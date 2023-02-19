import cn from 'classnames';
import './Loading.scss';
import soyzLogo from './soyzLogo.webp';

const useLoading = () => {
  return {};
};
const LoadingView = (props) => {
  return (
    <>
      <img className="loading__img" src={soyzLogo} alt="Soyz logo" />
    </>
  );
};
const Loading = () => {
  const props = useLoading();

  return <LoadingView {...props} />;
};
export default Loading;
