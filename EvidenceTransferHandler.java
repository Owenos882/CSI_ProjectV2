package CSI_Project;

import javax.swing.*;
import java.awt.datatransfer.*;

public class EvidenceTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        JList<?> list = (JList<?>) c;
        Object value = list.getSelectedValue();
        return new StringSelection(value.toString());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }
}
