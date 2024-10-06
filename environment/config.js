var config = {};

config.port = 3000;
config.reporting = "http://localhost:8080";
config.decisioning = "http://localhost:8081";

config.headless = false; // this can be set to true in automated testing
config.decisioningStrategy = 'lil_ucb'; 
config.assetId = 1;

module.exports = config;
