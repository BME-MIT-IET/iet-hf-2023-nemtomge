Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/jammy64"
  config.disksize.size = '41GB'
  config.vm.provision :shell, :inline => "sed -i 's|deb http://archive.ubuntu.com.ubuntu|deb mirror://mirrors.ubuntu.com/mirrors.txt|g' /etc/apt/sources.list"
  config.vm.provision "shell", inline: "sed -i '/deb-src/d' /etc/apt/sources.list"
 
  #config.vm.network "public_network"

  config.vm.provider "virtualbox" do |vb|
    vb.gui = true
    vb.memory = "2048"
    vb.cpus = 2

    vb.customize ["modifyvm", :id, "--uartmode1", "disconnected"]
    vb.customize ["modifyvm", :id, "--audio", "none"]
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update
    sudo apt-get install -y git
    sudo apt-get install -y lubuntu-desktop
  SHELL

  config.vm.provision :ansible_local do |ansible|
    ansible.playbook = "playbook.yml"
    ansible.provisioning_path = "/vagrant"
  end

  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get install -y xkb-data
    setxkbmap -layout hu
    echo "Done!"
    echo "run vagrant reload"
  SHELL

end
