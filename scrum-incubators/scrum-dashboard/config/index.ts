export default {
    development: {
        mode: 'dev',
        cdn: "./",
        mocked: true,
        port: 9000,
        apiBaseUrl: "http://localhost:3000"
    },
    production: {
        mode: 'prod',
        cdn: "",
        mocked: false,
        apiBaseUrl: "http://localhost:3000"
    }
}