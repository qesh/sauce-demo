FROM jenkins/jenkins:lts-jdk21

USER root

# Maven + Google Chrome (Selenium Manager downloads the matching chromedriver at runtime)
RUN apt-get update \
    && apt-get install -y --no-install-recommends maven wget gnupg ca-certificates \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

USER jenkins

# Plugins: pipeline, git, HTML report publishing, cucumber reports
RUN jenkins-plugin-cli --plugins workflow-aggregator git htmlpublisher cucumber-reports
