
import { useState } from 'react';
import { ArrowLeft, Save, Send, FileText } from 'lucide-react';
import { Link } from 'react-router-dom';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';

const AuthorSubmission = () => {
  const [formData, setFormData] = useState({
    title: '작품 제목을 입력하세요',
    category: '로맨스', 
    content: '여기에 작품을 작성해주세요.\n\n팁:\n- 단락 구분을 명확히 해주세요\n- 대화는 따옴표로 구분해주세요\n- 최소 1,000자 이상 작성해야 최종저장이 가능합니다'
  });

  const [status, setStatus] = useState({
    isSaved: false,
    wordCount: 0,
    lastSaved: null as Date | null
  });

  const categories = ['로맨스', '판타지', '스릴러', '힐링', '성장', '역사', 'SF'];

  const handleSave = (type: 'temp' | 'final') => {
    const wordCount = formData.content.replace(/\s/g, '').length;
    setStatus({
      isSaved: true,
      wordCount,
      lastSaved: new Date()
    });
    
    if (type === 'temp') {
      alert('임시저장되었습니다.');
    } else if (wordCount >= 1000) {
      alert('최종저장되었습니다.');
    } else {
      alert('최소 1,000자 이상 작성해야 최종저장이 가능합니다.');
    }
  };

  return (
    <div className="min-h-screen bg-warm-brown-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-8">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              새 글 작성
            </h1>
            <p className="text-lg text-gray-600">
              창의적인 이야기를 자유롭게 작성해보세요
            </p>
          </div>
          
          <div className="bg-white rounded-lg shadow-sm border border-warm-brown-200 overflow-hidden">
            <div className="p-6 border-b border-warm-brown-200">
              <div className="grid md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    제목 *
                  </label>
                  <input
                    type="text"
                    value={formData.title}
                    onChange={(e) => setFormData({...formData, title: e.target.value})}
                    className="w-full px-4 py-2 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                  />
                </div>
                
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    장르 *
                  </label>
                  <select 
                    value={formData.category}
                    onChange={(e) => setFormData({...formData, category: e.target.value})}
                    className="w-full px-4 py-2 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                  >
                    {categories.map(cat => (
                      <option key={cat} value={cat}>{cat}</option>
                    ))}
                  </select>
                </div>
              </div>
              
              <div className="mt-4 flex justify-between items-center text-sm text-gray-500">
                <div className="flex gap-4">
                  <span>상태: {status.isSaved ? '저장됨' : '임시저장'}</span>
                  <span>글자 수: {formData.content.replace(/\s/g, '').length}자</span>
                </div>
                {status.lastSaved && (
                  <span>마지막 저장: {status.lastSaved.toLocaleTimeString()}</span>
                )}
              </div>
            </div>
            
            <div className="p-6">
              <Textarea
                value={formData.content}
                onChange={(e) => setFormData({...formData, content: e.target.value})}
                rows={20}
                className="w-full border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent resize-none text-base leading-relaxed"
              />
            </div>
            
            <div className="p-6 border-t border-warm-brown-200 flex justify-between">
              <Button 
                variant="outline" 
                onClick={() => handleSave('temp')}
                className="flex items-center gap-2 border-warm-brown-300 text-warm-brown-700"
              >
                <Save className="w-4 h-4" />
                임시저장
              </Button>
              
              <Button 
                onClick={() => handleSave('final')}
                className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white flex items-center gap-2"
              >
                <Send className="w-4 h-4" />
                최종저장
              </Button>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default AuthorSubmission;
