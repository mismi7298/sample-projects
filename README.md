# npm-sbom-vuln-demo

A small Node.js project for exercising SBOM generation and vulnerability
scanning tools. Its `package.json` pins five dependencies to versions with
known, publicly disclosed vulnerabilities — each one fixable by upgrading to
a later version **without a semver-major bump** (confirmed via
`npm audit --json`, `fixAvailable.isSemVerMajor: false` for all five).

## Dependencies and known vulnerabilities

| Package     | Installed | Issue                                              | Severity | Fixed in | Bump  |
|-------------|-----------|-----------------------------------------------------|----------|----------|-------|
| lodash      | 4.17.15   | Prototype pollution (CVE-2020-8203), command injection in `template` (CVE-2021-23337) | high | 4.18.1 | patch |
| minimist    | 1.2.5     | Prototype pollution (CVE-2021-44906)                | critical | 1.2.8    | patch |
| axios       | 0.21.1    | ReDoS (CVE-2021-3749) and related 0.21.x issues     | high     | 0.21.4   | patch |
| qs          | 6.5.2     | Prototype pollution (CVE-2022-24999)                | high     | 6.15.3   | patch |
| node-fetch  | 2.6.0     | Exposure of sensitive info via redirect (CVE-2022-0235) | high | 2.7.0    | patch |

## Usage

```bash
npm install
npm start          # runs index.js, which exercises each dependency
npm audit          # see the vulnerability report
npm audit fix      # applies the patch-level upgrades above
```

## Generating an SBOM

Any standard Node/npm SBOM tool works against this repo's `package.json` /
`package-lock.json`, e.g.:

```bash
# CycloneDX
npx @cyclonedx/cyclonedx-npm --output-file sbom.cdx.json

# Syft
syft dir:. -o cyclonedx-json > sbom.cdx.json
syft dir:. -o spdx-json > sbom.spdx.json
```

The generated SBOM can then be fed into a vulnerability scanner (e.g. Grype,
Trivy, `osv-scanner`) to confirm the findings above and validate that
re-running the scan after `npm audit fix` shows them resolved.
