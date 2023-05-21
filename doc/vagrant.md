# Vagrant Virutal Machine

- [Vagrant Virutal Machine](#vagrant-virutal-machine)
  - [Summary](#summary)
  - [Pre-requisites](#pre-requisites)
  - [Setup](#setup)
  - [Run](#run)

## Summary
Vagrant is a tool for creating virtual machines with a single command. The `Vagrantfile` describes the VM. In this project it starts an Ubuntu 20.04 LTS machine with 2GB RAM and 2 CPU cores. It updates the system, installs git and lubuntu-desktop (lxde) as a desktop environment.

Using Ansible which is a configuration management tool mainly for devops purposes, installs snap and java 17.

## Pre-requisites
1. Download and install Vagrant from [here](https://www.vagrantup.com/downloads)
1. Install disksize plugin for vagrant: `vagrant plugin install vagrant-disksize`
1. Download and install VirtualBox from [here](https://www.virtualbox.org/wiki/Downloads)

## Setup
1. In the repository root run `vagrant up`
2. Wait for the machine to boot up
3. Wait for the machine to provision (this can take a while)
4. When the machine is ready, run `vagrant reload`. This will be written also in the opened terminal.
5. Login using **user**: `vagrant`, **password**: `vagrant`

## Run
1. Open a terminal
2. Navigate to the project root: `cd /vagrant`
3. Compile the project: `javac -cp ".:jars/json-simple-1.1.1.jar" $(find src -name '*.java' ! -name '._*') -d bin/`
4. Run the project: `java -cp ".:jars/json-simple-1.1.1.jar:bin/" main.Main`