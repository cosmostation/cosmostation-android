BNSD_VERSION=1.0.0

rm -rf spec gen

wget https://github.com/iov-one/weave/releases/download/v${BNSD_VERSION}/weave-v${BNSD_VERSION}-proto-spec.tar.gz -O weave-proto-spec.tar.gz
tar xzvf weave-proto-spec.tar.gz
rm -rf weave-proto-spec.tar.gz

docker run --rm -v $(pwd):/work iov1/prototool:0.2.2 prototool generate


