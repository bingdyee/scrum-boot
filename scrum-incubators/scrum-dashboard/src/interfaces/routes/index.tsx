import React, { Suspense } from "react";
import { Routes, Route,  } from 'react-router-dom';
import { CircularProgress } from '@mui/material';


const HomePage = React.lazy(() => import("@/interfaces/views/HomeView"));

const RootRoutes = () => {
	return (
		<Suspense fallback={<CircularProgress />}>
			<Routes>
				<Route path="/" element={ <HomePage />  } />
			</Routes>
		</Suspense>
	);
};


export { RootRoutes };
