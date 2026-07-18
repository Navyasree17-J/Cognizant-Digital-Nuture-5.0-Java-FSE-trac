import { createContext } from 'react';

// Create a new context initialized with a default fallback state of 'light'
const ThemeContext = createContext('light');

export default ThemeContext;