const _ = require('lodash');
const minimist = require('minimist');
const axios = require('axios');
const qs = require('qs');
const fetch = require('node-fetch');

function demoLodash() {
  const defaults = { retries: 3, timeout: 1000 };
  const options = _.merge({}, defaults, { timeout: 5000 });
  console.log('[lodash] merged options:', options);
}

function demoMinimist() {
  const args = minimist(process.argv.slice(2));
  console.log('[minimist] parsed args:', args);
}

function demoQs() {
  const parsed = qs.parse('user=naresh&role=admin&tags[]=sbom&tags[]=demo');
  console.log('[qs] parsed query string:', parsed);
}

async function demoAxios() {
  try {
    const res = await axios.get('https://httpbin.org/get', { timeout: 3000 });
    console.log('[axios] request succeeded, status:', res.status);
  } catch (err) {
    console.log('[axios] request skipped/failed:', err.message);
  }
}

async function demoNodeFetch() {
  try {
    const res = await fetch('https://httpbin.org/get', { timeout: 3000 });
    console.log('[node-fetch] request succeeded, status:', res.status);
  } catch (err) {
    console.log('[node-fetch] request skipped/failed:', err.message);
  }
}

async function main() {
  demoLodash();
  demoMinimist();
  demoQs();
  await demoAxios();
  await demoNodeFetch();
}

main();
