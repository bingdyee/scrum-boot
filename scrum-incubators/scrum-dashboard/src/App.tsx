import React from "react";
import { HashRouter as Router } from "react-router-dom";
import { ThemeProvider } from "@mui/material/styles";

import theme from "@/infrastructure/theme";
import { RootRoutes } from "@/interfaces/routes";


function App() {
  return (
    <ThemeProvider theme={theme}>
      <Router>
        <RootRoutes />
      </Router>
    </ThemeProvider>
  )
}

export default App;
