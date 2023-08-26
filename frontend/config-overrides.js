const { getLoader } = require("react-app-rewired");
const lessLoader = {
  test: /\.less$/,
  use: ["style-loader", "css-loader", "less-loader"],
};

module.exports = function override(config, env) {
  const oneOfRule = config.module.rules.find((rule) =>
    Array.isArray(rule.oneOf)
  );

  if (oneOfRule) {
    oneOfRule.oneOf.unshift(lessLoader);
  } else {
    // Fallback to adding the lessLoader to the list of loaders
    config.module.rules.push(lessLoader);
  }

  return config;
};
