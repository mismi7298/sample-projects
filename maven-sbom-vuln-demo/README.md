# maven-sbom-vuln-demo

A small Maven/Java project for exercising SBOM generation and vulnerability
scanning tools. Its `pom.xml` pins five dependencies, three of which have
known, publicly disclosed vulnerabilities at the pinned version; the other
two are clean.

## Dependencies

| Dependency                          | Version | Status     | Issue                                                              | Severity | Fixed in |
|--------------------------------------|---------|------------|---------------------------------------------------------------------|----------|----------|
| org.apache.logging.log4j:log4j-core  | 2.14.1  | vulnerable | "Log4Shell" RCE via JNDI lookups (CVE-2021-44228), plus CVE-2021-45046, CVE-2021-45105 | critical | 2.17.1   |
| commons-collections:commons-collections | 3.2.1 | vulnerable | Unsafe deserialization via `InvokerTransformer`, RCE (CVE-2015-6420) | critical | 3.2.2    |
| org.apache.commons:commons-text      | 1.9     | vulnerable | "Text4Shell" RCE via `StringSubstitutor` script/url/dns interpolation (CVE-2022-42889) | critical | 1.10.0   |
| org.apache.commons:commons-lang3     | 3.12.0  | clean      | —                                                                    | —        | —        |
| com.google.code.gson:gson            | 2.8.9   | clean      | —                                                                    | —        | —        |

All three fixes are same-line upgrades (no groupId/artifactId change).

## Usage

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.example.sbomdemo.App"
```

## Generating an SBOM

```bash
# CycloneDX Maven plugin
mvn org.cyclonedx:cyclonedx-maven-plugin:makeAggregateBom

# OWASP dependency-check
mvn org.owasp:dependency-check-maven:check
```

Feed the generated SBOM (`target/bom.xml` / `target/bom.json`, or the
dependency-check report) into a scanner such as Grype, Trivy, or
`osv-scanner` to confirm the findings above, then bump the three vulnerable
dependencies to their fixed versions and re-scan to verify they clear.
