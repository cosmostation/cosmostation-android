package wannabit.io.cosmostaion.utils.hdac;

import org.bitcoinj.core.BitcoinSerializer;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.params.AbstractBitcoinNetParams;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.MonetaryFormat;

public class HdacNetworkParams extends AbstractBitcoinNetParams {
    public String addressChecksumValue;
    protected long time;


    public HdacNetworkParams() {
        this.addressHeader = 40;
        this.p2shHeader = 8;
        this.addressChecksumValue = "48444143";

        this.interval = 2016;
        this.targetTimespan = 1209600;
        this.maxTarget = Utils.decodeCompactBits(486604799L);

        this.dumpedPrivateKeyHeader = 131;

        this.port = 22009;
        this.packetMagic = 4190024921L;

        this.id = "org.bitcoin.production";
        this.subsidyDecreaseBlockCount = 210000;
        this.spendableCoinbaseDepth = 100;
        this.time = (System.currentTimeMillis() / 1000L);
    }

    public static synchronized HdacNetworkParams getDefault() {
        return new HdacNetworkParams();
    }

    public String getAddressChecksumValue() {
        return this.addressChecksumValue;
    }

    public String getPaymentProtocolId() {
        return "main";
    }

    @Override
    public void checkDifficultyTransitions(StoredBlock storedPrev, Block next, BlockStore blockStore) throws VerificationException, BlockStoreException {

    }


    public Coin getMaxMoney() {
        return null;
    }

    public Coin getMinNonDustOutput() {
        return null;
    }

    public MonetaryFormat getMonetaryFormat() {
        return null;
    }

    public int getDumpedPrivateKeyHeader() {
        return this.dumpedPrivateKeyHeader;
    }

    public int getProtocolVersionNum(NetworkParameters.ProtocolVersion arg0) {
        return 0;
    }

    public BitcoinSerializer getSerializer(boolean parseRetain) {
        return new BitcoinSerializer(this, parseRetain);
    }

    public String getUriScheme() {
        return null;
    }

    public boolean hasMaxMoney() {
        return false;
    }
}
