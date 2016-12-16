// @author Johnathan Steven Salamanca Lancheros
chrome.browserAction.onClicked.addListener(function(tab) {
  chrome.tabs.executeScript(null, {file: "./myscript.js"});
  chrome.tabs.executeScript(null, {file: "./script2.js"});
});
