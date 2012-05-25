package cn.m15.list;
import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * by:������
 * email:xuanyusong@qq.com
 * @author Administrator
 *
 */

public class MainDialog extends Activity implements Runnable{
   
    /**ȷ��ȡ����Ϣ�� **/
    private static final int DIALOG_0 = 1;
    /**�����ť��Ϣ�� **/
    private static final int DIALOG_1 = 2;
    /**�б�� **/
    private static final int DIALOG_2 = 3;
    /**�������� **/
    private static final int DIALOG_3 = 4;
    /**����ѡ���б�� **/
    private static final int DIALOG_4 = 5;
    /**����ѡ���б�� **/
    private static final int DIALOG_5 = 6;
    /**�Զ��岼�� **/
    private static final int DIALOG_6 = 7;
    /**��ȡ���ȿ� **/
    private static final int DIALOG_7 = 8;
    
    private static final int MAX_PROGRESS = 100;
    
    private ProgressDialog mProgressDialog  = null;
    
    final String[] mItems = {"item0","item1","itme2","item3","itme4","item5","item6"};
    
    int mSingleChoiceID = -1;
    
    ArrayList <Integer>MultiChoiceID = new ArrayList <Integer>();
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_0);
            }
        });
        
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_1);
            }
        });
        
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_2);
            }
        });
    
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_3);
        	mProgressDialog.setProgress(0);
            }
        });
     
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_4);
            }
        });  
        
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_5);
            }
        }); 
    
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_6);
            }
        }); 
        
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
        	CreatDialog(DIALOG_7);
            }
        }); 
    }

    public void CreatDialog(int id) {
	AlertDialog.Builder builder = new AlertDialog.Builder(MainDialog.this);	
	switch(id) {
	case DIALOG_0:
	    builder.setIcon(R.drawable.icon);
            builder.setTitle("��ȷ��Ҫ�뿪��");
            builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //������ӵ��ȷ������߼�
                    showDialog("��ѡ����ȷ��");
                }
            });
            builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //������ӵ��ȷ������߼�
                    showDialog("��ѡ����ȡ��");
                }
            });
	    break;
	case DIALOG_1:
	    builder.setIcon(R.drawable.icon);
            builder.setTitle("ͶƱ");
            builder.setMessage("����Ϊʲô������������������");
            builder.setPositiveButton("��Ȥζ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    showDialog("��ѡ������Ȥζ��");
                }
            });
            builder.setNeutralButton("��˼���", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    showDialog("��ѡ������˼���");                    
                }
            });
            builder.setNegativeButton("����ǿ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    showDialog("��ѡ��������ǿ��");  
                }
            });
	    break;
	case DIALOG_2:
	    builder.setTitle("�б�ѡ���");
            builder.setItems(mItems, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //����󵯳�����ѡ���˵ڼ���
                    showDialog("��ѡ���idΪ" + which + " , " + mItems[which]);
                }
            });
	    break;
	case DIALOG_3:
	            mProgressDialog = new ProgressDialog(MainDialog.this);
	            mProgressDialog.setIcon(R.drawable.icon);
	            mProgressDialog.setTitle("����������");
	            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	            mProgressDialog.setMax(MAX_PROGRESS);
	            mProgressDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                    //������ӵ������߼�
	                }
	            });
	            mProgressDialog.setButton2("ȡ��", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                    //������ӵ������߼�
	                }
	            });
	            mProgressDialog.show();
	            new Thread(this).start();
	     return;
	case DIALOG_4:
	     mSingleChoiceID = -1;
	     builder.setIcon(R.drawable.icon);
             builder.setTitle("����ѡ��");
             builder.setSingleChoiceItems(mItems, 0, new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
                         mSingleChoiceID = whichButton;
                         showDialog("��ѡ���idΪ" + whichButton + " , " + mItems[whichButton]);
                 }
             });
             builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
                     if(mSingleChoiceID > 0) {
                	 showDialog("��ѡ�����" + mSingleChoiceID);
                     }
                 }
             });
             builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {

                 }
             });
	    break;
	case DIALOG_5:
	    MultiChoiceID.clear();
	    builder.setIcon(R.drawable.icon);
            builder.setTitle("����ѡ��");
            builder.setMultiChoiceItems(mItems,
                    new boolean[]{false, false, false, false, false, false, false},
                    new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton,
                                boolean isChecked) {
                           if(isChecked) {
                               MultiChoiceID.add(whichButton);
                               showDialog("��ѡ���idΪ" + whichButton + " , " + mItems[whichButton]);
                           }else {
                               MultiChoiceID.remove(whichButton);
                           }
                            
                        }
                    });
            builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String str = "";
                    int size = MultiChoiceID.size();
                    for (int i = 0 ;i < size; i++) {
                	str+= mItems[MultiChoiceID.get(i)] + ", ";
                    }
                    showDialog("��ѡ�����" + str);
                }
            });
            builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });
	    break;
	case DIALOG_6:
            LayoutInflater factory = LayoutInflater.from(this);
            final View textEntryView = factory.inflate(R.layout.test, null);
            	builder.setIcon(R.drawable.icon);
                builder.setTitle("�Զ��������");
                builder.setView(textEntryView);
                builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                	
                	EditText userName = (EditText) textEntryView.findViewById(R.id.etUserName);
                	EditText password = (EditText) textEntryView.findViewById(R.id.etPassWord);
                	showDialog("���� ��"  + userName.getText().toString()  + "���룺" + password.getText().toString() );
                    }
                });
                builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
	    break;
	case DIALOG_7:
	    mProgressDialog = new ProgressDialog(this);
	    mProgressDialog.setTitle("��ȡing");
	    mProgressDialog.setMessage("���ڶ�ȡ�����Ժ�");
	    mProgressDialog.setIndeterminate(true);
	    mProgressDialog.setCancelable(true);
	    mProgressDialog.show();
	    return;
	}
	builder.create().show();
    }

    
    private void showDialog(String str) {
	 new AlertDialog.Builder(MainDialog.this)
         .setMessage(str)
         .show();
    }
    
    @Override
    public void run() {
	    int Progress = 0;
	    while(Progress < MAX_PROGRESS) {
		try {
		    Thread.sleep(100);
		    Progress++;  
		    mProgressDialog.incrementProgressBy(1);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		 
	    }
	
    }








}