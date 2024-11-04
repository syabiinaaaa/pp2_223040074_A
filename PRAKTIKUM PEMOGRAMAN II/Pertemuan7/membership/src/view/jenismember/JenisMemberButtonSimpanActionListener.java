package Pertemuan7.membership.src.view.jenismember;

import java.awt.event.*;
import java.util.UUID;
import Pertemuan7.membership.src.dao.JenisMemberDao;
import Pertemuan7.membership.src.model.JenisMember;

public class JenisMemberButtonSimpanActionListener {
  private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;
    
    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }
    
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);
        
        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.jenisMemberDao.insert(jenisMember);
    }
}