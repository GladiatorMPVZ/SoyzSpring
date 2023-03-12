import React, { ReactElement, KeyboardEvent, MouseEvent } from 'react';
import ReactDOM from 'react-dom';

const useModal = (props: Parameters<typeof Modal>[0]) => {
  const container = document.getElementById('overlay') as HTMLElement;
  const handleClick = (e: MouseEvent | KeyboardEvent) => e.target === e.currentTarget && props.onClick();
  return { container, children: props.children, handleClick };
};

const ModalView = (props: ReturnType<typeof useModal>) => {
  return ReactDOM.createPortal(
    <div className="modal" role="button" tabIndex={0} onClick={props.handleClick} onKeyDown={props.handleClick}>
      {props.children}
    </div>,
    props.container,
  );
};

export default function Modal(props: { children: ReactElement; onClick: () => void }) {
  return <ModalView {...useModal(props)} />;
}
