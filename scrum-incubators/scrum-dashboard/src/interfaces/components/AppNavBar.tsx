import React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';


export default function AppNavBar() {
	return (
		<Box sx={{ flexGrow: 1 }}  style={{ backgroundColor: '#f7f7f7', opacity: 0.5, margin: "10px 100px" }}>
			<AppBar position="static" style={{ borderRadius: 10 }}>
				<Toolbar>
					<Typography
						variant="h5"
						noWrap
						component="div"
						sx={{ display: { xs: 'none', sm: 'block' } }}
					>
						Sci Meta
					</Typography>
					<Box sx={{ flexGrow: 1 }} />
					<Box sx={{ display: { xs: 'none', md: 'flex' } }}>
						<Button variant="outlined" color="inherit" startIcon={<DeleteIcon />}>
							登录
						</Button>
						<Button variant="outlined" color="inherit" startIcon={<DeleteIcon />}>
							注册
						</Button>
					</Box>
				</Toolbar>
			</AppBar>
		</Box>
	)
}