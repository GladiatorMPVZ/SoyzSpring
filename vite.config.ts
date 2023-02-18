import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react-swc';

export default defineConfig({
  root: 'src/client',
  plugins: [react()],
  css: {
    devSourcemap: true,
  },
});
