import React from "react";
import { AppBar, IconButton, Menu, MenuItem, Toolbar } from "@mui/material";
import { Box } from "@mui/system";
import AccountCircle from '@mui/icons-material/AccountCircle';
import { Link } from "react-router-dom";
import { logOutUser } from "../../features/accounts/accountsSlice";
import { useAppDispatch } from "../../features/hooks";

const actionDispatch = (dispatch) => ({
  logOutUser: () => dispatch(logOutUser()),
});

const AdminNav = ({NavButton, LogoButton}) => {

    const [anchorEl, setAnchorEl] = React.useState(null);

    const isMenuOpen = Boolean(anchorEl);

    const { logOutUser } = actionDispatch(useAppDispatch());

    const handleProfileMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    const LogOut = () => {
      logOutUser();
    }

    const menuId = 'primary-search-account-menu';
    return(
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" sx={{
        bgcolor:'black',
      }}>
        <Toolbar>
          <Link to="/" style={{textDecoration:'none'}}>
            <LogoButton
              component="div"
              sx={{ display: { sm: 'block' } }}
            >
              $alary Checker
            </LogoButton>
          </Link>
          <Box sx={{ flexGrow: 1 }} />
          <Box sx={{ display: 'flex' }}>
            <Link to="/create-user">
              <NavButton variant="outlined">
                Opprett bruker
              </NavButton>
            </Link>
            <Link to="/users">
              <NavButton variant="outlined" sx={{margin:'0.5rem', color:'white'}}>
                Brukere
              </NavButton>
            </Link>
          </Box>
          <IconButton
            size="large"
            edge="end"
            aria-label="account of current user"
            aria-controls={menuId}
            aria-haspopup="true"
            onClick={handleProfileMenuOpen}
            color="inherit"
          >
            <AccountCircle />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Menu
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        id={menuId}
        keepMounted
        transformOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        open={isMenuOpen}
        onClose={handleMenuClose}    
      >
        <MenuItem onClick={LogOut}>Logg ut</MenuItem>
    </Menu>
    </Box>
    );
}

export default AdminNav;
