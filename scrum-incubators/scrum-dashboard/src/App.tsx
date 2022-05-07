import React from "react";
import { HashRouter as Router } from "react-router-dom";
import { ThemeProvider } from "@mui/material/styles";
import { SnackbarProvider } from 'notistack';

import theme from "@/infrastructure/theme";
import { RootRoutes } from "@/interfaces/routes";


function App() {
  return (
    <ThemeProvider theme={theme}>
      <SnackbarProvider maxSnack={3} anchorOrigin={{
        vertical: 'top',
        horizontal: 'center',
      }}>
        <Router>
          <RootRoutes />
        </Router>
      </SnackbarProvider>
    </ThemeProvider>
  )
}

export default App;
