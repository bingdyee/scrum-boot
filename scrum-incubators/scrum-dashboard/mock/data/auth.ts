const menuList = [
    {
        "menuId": "1",
        "label": "控制台",
        "icon": "dashboard",
        "link": "/dashboard",
    },
    {
        "menuId": "2",
        "label": "系统管理",
        "icon": "admin",
        "link": "/admin",
        "children": [
            {
                "menuId": "3",
                "label": "用户管理",
                "icon": "user",
                "link": "/user",
            },
            {
                "menuId": "4",
                "label": "角色管理",
                "icon": "role",
                "link": "/role",
            }
        ]
    }
]


export default [
    {
        url: '/v1/auth/login',
        type: 'post',
        response: () => {
            return {
                "code": 2000,
                "success": true,
                "data": {
                    "access_token": "c62d15ba-121e-494d-b6f1-5f20afca3df0",
                    "refresh_token": "4923f460-6967-472c-be49-69f76fda825a",
                    "expireIn": 7200,
                    "type": "bearer"
                }
            }
        }
    },
    {
        url: '/v1/auth/user-info',
        type: 'get',
        response: () => {
            return {
                "code": 2000,
                "success": true,
                "data": {
                    "userId": "1",
                    "username": "admin",
                    "phone": "176****8676",
                    "avatar": "http://dummyimage.com/64x64",
                    "roles": [
                        {
                            "roleId": "1",
                            "code": "ROLE_ADMIN",
                            "privileges": ["CREATE", "UPDATE", "DELETE", "QUERY"],
                        }
                    ],
                    "menus": menuList
                }
            }
        }
    }
];